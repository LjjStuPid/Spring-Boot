package com.liujj.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Author: liujinjian
 * @Date: 2020/6/2 18:36
 */
public class ClientSample {

    public static void main(String[] args) {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1",
                11111), "example", "", "");
        int batchSize = 1000;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe(".*\\..");
            connector.rollback();
            int totalEmtryCount = 1200;
            while (emptyCount < totalEmtryCount) {
                Message message = connector.getWithoutAck(batchSize);
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    System.out.println("empty count: " + emptyCount);
                    Thread.sleep(1000);
                } else {
                    emptyCount = 0;
                    printEntry(message.getEntries());

                }
                connector.ack(batchId);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }
            CanalEntry.RowChange rowChange = null;
            try {
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            CanalEntry.EventType eventType = rowChange.getEventType();
            System.out.println(String.format("==============binlog[%s:&s],name[%s,%s],eventType:%s", entry.getHeader().getLogfileName()
                    , entry.getHeader().getLogfileOffset(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));
            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else {
                    System.out.println("----------->before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("----------->after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    private static void printColumn(List<CanalEntry.Column> columnList) {
        for (CanalEntry.Column column : columnList) {
            System.out.println(column.getName() + ":" + column.getValue() + " update=" + column.getUpdated());
        }
    }
}
