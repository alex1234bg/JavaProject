package org.alexanderpetrovF113655.store.util;
import org.alexanderpetrovF113655.store.model.Receipt;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class ReceiptFileManager implements Serializable{
    private static final String TEXT_FOLDER = "receipts/";
    private static final String SERIAL_FOLDER = "receipts_serial/";

    static {
        new File(TEXT_FOLDER).mkdirs();
        new File(SERIAL_FOLDER).mkdirs();

    }

    public static void saveReceiptAsText(Receipt receipt) throws IOException{
        String filename = TEXT_FOLDER + "receipt_" + receipt.getReceiptNumber() + ".txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(receipt.toString());
        }
    }
    public static void saveReceiptSerialized(Receipt receipt) throws IOException{
        String filename = SERIAL_FOLDER + "receipt_" + receipt.getReceiptNumber() + ".ser";
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(receipt);
        }

}
public static Receipt loadReceiptSerialized(int receiptNumber) throws IOException, ClassNotFoundException{
    String filename = SERIAL_FOLDER + "receipt_" + receiptNumber + ".ser";
    try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
       return (Receipt) in.readObject();
    }
}
public static String readReceiptText(int receiptNumber ) throws IOException{
        String filename = TEXT_FOLDER + "receipt_" + receiptNumber + ".txt";
        return Files.readString(Paths.get(filename));
}

}
