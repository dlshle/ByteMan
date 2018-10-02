package bytereader;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Vector;

/**
 *
 * @author Xuri Li 
 */
public class Console {

    private File file;
    private byte[] dec = null;
    private String[] hex = null;
    private String[] bin = null;
    private char[] chr = null;
    private int[] ci = null;
    private int bCount = 0;
    private int totLen = 0;

    public Console(String path) {
        loadFile(path);
    }
    
    public int getBCount(){
        return bCount;
    }

    public byte[] getDec() {
        return dec;
    }

    public String[] getHex() {
        return hex;
    }

    public String[] getBin() {
        return bin;
    }

    public char[] getChr() {
        return chr;
    }

    public int[] getCi() {
        return ci;
    }  
        
    public String getFileInfo(){
        if(isAvaliable()){
            return "file name:"+file.getName()+"\nfile path:"+file.getAbsolutePath()+"\nlength of byte stream:" + dec.length + "\nlength of character stream:" + chr.length + "\nfile size:" + dec.length*8 +"bits/"+dec.length+"bytes.";
        }
        return "";
    }
    
    public boolean isAvaliable(){
        return file!=null;
    }

    public String executeCommand(String command) {
        String line = command.trim();
        try {
            switch (line) {
                case "b":
                    return strArrToString(bin);
                case "h":
                    return strArrToString(hex);
                case "d":
                    return byteArrToString(dec);
                case "c":
                    return charArrToString(chr);
                case "ci":
                    return intArrToString(ci);
                case "la":
                    //list all
                    return listAll();
                case "rcmp":
                    return rowCmp();
                case "help":
                    return getHelp();
                case "exit":
                    file = null;
                    dec = null;
                    bin = null;
                    hex = null;
                    chr = null;
                    ci = null;
                    bCount = 0;
                    totLen = 0;
                    return "console closed.";
            }
            String args = null;      
            if(line.startsWith("load")){
                args = line.substring(4);
                return loadFile(args)?args+" has been loaded to the console.":"Can not read the file.";
            }
            if(line.startsWith("cmp")){
                args = line.substring(3).trim();
                return cmp(args);
            }
            if(line.startsWith("c2i")){
                args = line.substring(3).trim();
                return String.valueOf(c2i(args.charAt(0)));
            }
            if(line.startsWith("i2c")){
                args = line.substring(3).trim();
                return String.valueOf(i2c(Integer.valueOf(args)));
            }
            if(line.startsWith("c2byte")){
                args = line.substring(4).trim();
                return String.valueOf(c2byte(args.charAt(0)));
            }
            if(line.startsWith("c2b")){
                args = line.substring(3).trim();
                return c2b(args.charAt(0));
            }
            if(line.startsWith("byte2c")){
                args = line.substring(6).trim();
                return String.valueOf(byte2c(Byte.valueOf(args)));
            }
            if(line.startsWith("byte2b")){
                args = line.substring(6).trim();
                return byte2b(Byte.valueOf(args));
            }
            if(line.startsWith("i2h")){
                args = line.substring(3).trim();
                return i2h(Integer.valueOf(args));
            }
            if(line.startsWith("byte2h")){
                args = line.substring(6).trim();
                return byte2h(Byte.valueOf(args));
            }
            if(line.startsWith("h2byte")){
                args = line.substring(6).trim();
                return String.valueOf(h2byte(args));
            }
            if(line.startsWith("b2byte")){
                args = line.substring(6).trim();
                return String.valueOf(b2byte(args));
            }
            if(line.startsWith("h2i")){
                args = line.substring(3).trim();
                return String.valueOf(h2i(args));
            }
            if(line.startsWith("i2b")){
                args = line.substring(3).trim();
                return i2b(Integer.valueOf(args));
            }
            if(line.startsWith("h2b")){
                args = line.substring(3).trim();
                return h2b(args);
            }
            if(line.startsWith("b2i")){
                args = line.substring(3).trim();
                return String.valueOf(b2i(args));
            }
            if(line.startsWith("b2h")){
                args = line.substring(3).trim();
                return b2h(args);
            }            
            //saveb has to be before the save command as save<saveb
            if(line.startsWith("saveb")){
                args = line.substring(5);
                if (!writeBytes(args, dec)) {
                        return "Unable to save the file.";
                } else {
                        return "Save successd!";
                }
            }
            if(line.startsWith("save")){
                int lastSpace = line.lastIndexOf(" ");
                String path = line.substring(lastSpace+1);
                args = line.substring(0, lastSpace);
                args = args.substring(4);
                if (!save(args, path)) {
                        return "Unable to save the file.";
                } else {
                        return "Save successd!";
                }
            }
            if(line.startsWith("edit")){
                args = line.substring(line.indexOf(" ")+1);
                String[] argArr = args.split(" ");
                //edit row indicator val
                if(argArr.length!=3)
                    return "Invalid edit parameters!\n"+args;
                int row = Integer.valueOf(argArr[0].trim());
                char indicator = argArr[1].trim().toLowerCase().charAt(0);
                String val = argArr[2];
                if(!editRow(row, indicator, val))
                    return "Edit insuccesful";
                return "Row "+row+" has been edited.";
            }
            if(line.startsWith("searchf")){
                args = line.substring(line.indexOf(" ")+1).trim();
                String[] argArr = args.split(" ");
                if(argArr.length!=2)
                    return "Invalid search parameters!\n"+args;
                char col = argArr[0].toLowerCase().charAt(0);
                String val = argArr[1].trim();
                int result = searchFront(col,val);
                return result==-1?"-1 Can not find the target!":String.valueOf(result);
            }
            if(line.startsWith("searchb")){
                args = line.substring(line.indexOf(" ")+1).trim();
                String[] argArr = args.split(" ");
                if(argArr.length!=2)
                    return "Invalid search parameters!\n"+args;
                char col = argArr[0].toLowerCase().charAt(0);
                String val = argArr[1].trim();
                int result = searchBack(col,val);
                return result==-1?"-1 Can not find the target!":String.valueOf(result);
            }
            if(line.startsWith("show")){
                args = line.substring(line.indexOf(" ")+1);
                return show(Integer.valueOf(args));
            }
            return "unknown command";
        } catch (NumberFormatException nfe) {
            return "Can not parse the args of input " + line + " to a number.";
        } catch(NullPointerException npe){
            return "No file has been loaded or the file is not readable.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error reading the command.";
        }
    }
    
    public boolean loadFile(String path){
        file = new File(path);
        if (!file.exists()) {
            file = null;
            return false;
        } else {
            dec = readBytes(path);
            if (dec == null) {
                file = null;
                return false;
            } else {
                hex = convertHexStr(dec);
                bin = convertBinStr(dec);
                chr = bytesToChars(dec);
                ci = charToInt(chr);
                bCount = dec.length;
                totLen = bCount;
            }
        }
        return true;
    }
    
    public String show(int i){
        if(i<0||i>bCount)
            return "Invalid argument:"+i;
        StringBuffer buffer = new StringBuffer();
        buffer.append(i).append(":").append(dec[i]).append(" ").append(bin[i]).append(" ").append(hex[i]).append(" ").append(chr[i]).append(" ").append(ci[i]);
        return buffer.toString();
    }
    
    public String strArrToString(String[] arr){
        StringBuffer buffer = new StringBuffer();
        for(String s:arr){
            buffer.append(s).append(" ");
        }
        return buffer.toString();
    }
    
    public String charArrToString(char[] arr){
        StringBuffer buffer = new StringBuffer();
        for(Character c:arr){
            buffer.append(c).append(" ");
        }
        return buffer.toString();
    }
    
    public String intArrToString(int[] arr){
        StringBuffer buffer = new StringBuffer();
        for(Integer c:arr){
            buffer.append(c).append(" ");
        }
        return buffer.toString();
    }
    
    public String byteArrToString(byte[] arr){
        StringBuffer buffer = new StringBuffer();
        for(Byte c:arr){
            buffer.append(c).append(" ");
        }
        return buffer.toString();
    }
    
    public String listAll(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Decimal:\n");
        buffer.append(byteArrToString(dec));
        buffer.append("\nBinary:\n");
        buffer.append(strArrToString(bin));
        buffer.append("\nHexidecimal:\n");
        buffer.append(strArrToString(hex));
        buffer.append("\nCharacter:\n");
        buffer.append(charArrToString(chr));
        buffer.append("\nCharacter to Integer:\n");
        buffer.append(intArrToString(ci));
        return buffer.toString();
    }

    public String getHelp() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Commands:").append("\n");
        buffer.append("b for binary stream.\nh for hexidecimal stream.\nd for decimal stream.\n");
        buffer.append("c for char stream.\nci for char to int stream.\nla to list in all modes.\ncmp for compare.(e.g. cmp [ENTER] d c for comparing the byte decimals and characters)\nrcmp for row compare(with b,n,d,c,ci).\n");
        buffer.append("c2i for char to integer.\ni2c for integer to char.\nc2byte for char to byte.\nc2b for char to binary stream.\nbyte2c for byte to char.\nbyte2b for byte to binary stream.\n");
        buffer.append("i2h for integer to hexidecimal.\nh2i for hexidecimal to integer.\nbyte2h for byte to hexidecimal.\nh2byte for hexcidecimal to byte.\nb2byte for binary stream to byte.\n");
        buffer.append("i2b for integer to binary.\nh2b for hexidecimal to binary.\nb2i for binary to integer.\nb2h for binary to hexidecimal.\n");
        buffer.append("searchf indicator(d,b,h,c, or ci) content for search from front.\nsearchb indicator(d,b,h,c, or ci) content for search from end.\n");
        buffer.append("save indicator(d,b,h,c, or ci) file_path for save specific content to a specific file.\nsaveb file_path for saving the byte stream to a specific file.\n");
        return buffer.toString();
    }

    public byte[] readBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(32);
            byte[] b = new byte[32];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return buffer;
    }
    
    private boolean isVisibleChar(char c){
        return c>=20&&c<=126||c>127;
    }

    public String rowCmp() {
        int len = dec.length;
        if(!isAvaliable())
            return "Inavaliable file";
        StringBuffer buffer = new StringBuffer();
        int maxIndicatorLen = String.valueOf(len).length();
        for (int i = 0; i < len; i++) {
            buffer.append(appendSpaces(maxIndicatorLen - String.valueOf(i).length())).append(i).append(":").append(dec[i]).append(appendSpaces(4 - String.valueOf(dec[i]).length())).append(" ").append(bin[i]).append(appendSpaces(8 - bin[i].length())).append(" ").append(hex[i]).append(appendSpaces(3 - hex[i].length())).append(!isVisibleChar(chr[i])?' ':chr[i]).append(" ").append(ci[i]).append("\n");
        }
        return buffer.toString();
    }

    public String cmp(String arg) {
        String[] args = arg.split(" ");
        boolean d = false, b = false, h = false, c = false, cI = false;
        for (String s : args) {
            switch (s) {
                case "d":
                    d = true;
                    break;
                case "b":
                    b = true;
                    break;
                case "h":
                    h = true;
                    break;
                case "c":
                    c = true;
                    break;
                case "ci":
                    cI = true;
                    break;
                default:
                    return "unknown argument " + s;
            }
        }
        if(!d&&!b&&!c&&!h&&!cI&&!h)
            return "invalid argument";
        int len = dec.length;
        StringBuffer buffer = new StringBuffer();
        int maxIndicatorLen = String.valueOf(len).length();
        for (int i = 0; i < len; i++) {
            buffer.append(appendSpaces(maxIndicatorLen - String.valueOf(i).length())).append(i).append(":").append(d ? dec[i] : "").append(d ? appendSpaces(4 - String.valueOf(dec[i]).length()) : "").append(d ? " " : "").append(b ? bin[i] : "").append(b ? appendSpaces(8 - bin[i].length()) : "").append(b ? " " : "").append(h ? hex[i] : "").append(h ? appendSpaces(3 - hex[i].length()) : "").append(c ? (!isVisibleChar(chr[i])?' ':chr[i]) : "").append(c ? " " : "").append(cI ? ci[i] : "").append("\n");
        }
        return buffer.toString();
    }

    public boolean save(String arg, String path) {
        String subArg = null;
        if (arg == null || arg.isEmpty()) {
            return false;
        }
        arg = arg.trim().toLowerCase();
        if (!arg.contains(" ")) {
            switch (arg) {
                case "b":
                    return saveToFile(path, strArrToString(bin));
                case "h":
                    return saveToFile(path, strArrToString(hex));
                case "d":
                    return saveToFile(path, byteArrToString(dec));
                case "c":
                    return saveToFile(path, charArrToString(chr));
                case "ci":
                    return saveToFile(path, intArrToString(ci));                    
                case "la":
                    //list all
                    return saveToFile(path, listAll());
                case "rcmp":
                    return saveToFile(path, rowCmp());
                default:
                    return false;
            }
        } else {
            //with arguments
            if (arg.startsWith("cmp")) {
                    return saveToFile(path, cmp(arg.substring(arg.indexOf(" ")+1)));
            }
        }
        return false;
    }
    
    public boolean writeBytes(String path, byte[] content){
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File(path);
            if(!file.exists()){
                if(!file.createNewFile()){
                    System.out.println("Can not create the file.");
                    return false;
                }
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(content);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return false;
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public boolean saveToFile(String path, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path, true)));
            out.write(content);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error happened while creating the file.");
            return false;
        } finally {
            try {
                out.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Can not write content to the file.");
                return false;
            }
        }
    }

    public String appendSpaces(int num) {
        if (num <= 0) {
            return "";
        }
        return " " + appendSpaces(num - 1);
    }
    
    private void extendArrs(){
        byte[] newd = new byte[totLen+10];
        String[] newb = new String[totLen+10];
        String[] newh = new String[totLen+10];
        char[] newc = new char[totLen+10];
        int[] newci = new int[totLen+10];
        for(int i=0;i<bCount;i++){
            newd[i] = dec[i];
            newb[i] = bin[i];
            newh[i] = hex[i];
            newc[i] = chr[i];
            newci[i] = ci[i];
        }
        dec = newd;
        bin = newb;
        hex = newh;
        chr = newc;
        ci = newci;
        totLen+=10;
    }
    
    public Vector getRows(){
        Vector[] vs = new Vector[bCount];
        Vector result = new Vector();
        for(int i=0;i<bCount;i++){
            vs[i] = new Vector();
            vs[i].add(i+1);
            vs[i].add(dec[i]);
            vs[i].add(bin[i]);
            vs[i].add(hex[i]);
            vs[i].add(chr[i]);
            vs[i].add(ci[i]);
            result.add(vs[i]);
        }
        return result;
    }
    
    public int searchFront(char col, String val){
        switch (col) {
            case 'd':
                byte d = Byte.valueOf(val);
                for(int i=0;i<bCount;i++){
                    if(dec[i]==d)
                        return i;
                }
                return -1;
            case 'b':
                if (!isValidBin(val)) {
                    return -1;
                }
                String b = val;
                for(int i=0;i<bCount;i++){
                    if(bin[i].equals(b))
                        return i;
                }
                return -1;
            case 'h':
                if (!isValidHex(val)) {
                    return -1;
                }
                String h = val;
                for(int i=0;i<bCount;i++){
                    if(hex[i].equals(h))
                        return i;
                }
                return -1;
            case 'c':
                if (val.isEmpty() || val.length() > 1) {
                    return -1;
                }
                char c = val.charAt(0);
                for(int i=0;i<bCount;i++){
                    if(chr[i]==c)
                        return i;
                }
                return -1;
            case 'i':
                int j = Integer.valueOf(val);
                for(int i=0;i<bCount;i++){
                    if(ci[i]==j)
                        return i;
                }
                return -1;
        }
        return -1;
    }
    
    public int searchBack(char col, String val){
        switch (col) {
            case 'd':
                byte d = Byte.valueOf(val);
                for(int i=bCount-1;i>-1;i--){
                    if(dec[i]==d)
                        return i;
                }
                return -1;
            case 'b':
                if (!isValidBin(val)) {
                    return -1;
                }
                String b = val;
                for(int i=bCount-1;i>-1;i--){
                    if(bin[i].equals(b))
                        return i;
                }
                return -1;
            case 'h':
                if (!isValidHex(val)) {
                    return -1;
                }
                String h = val;
                for(int i=bCount-1;i>-1;i--){
                    if(hex[i].equals(h))
                        return i;
                }
                return -1;
            case 'c':
                if (val.isEmpty() || val.length() > 1) {
                    return -1;
                }
                char c = val.charAt(0);
                for(int i=bCount-1;i>-1;i--){
                    if(chr[i]==c)
                        return i;
                }
                return -1;
            case 'i':
                int j = Integer.valueOf(val);
                for(int i=bCount-1;i>-1;i--){
                    if(ci[i]==j)
                        return i;
                }
                return -1;
        }
        return -1;
    }
    
    private boolean isValidBin(String b){
        if(b.length()==0||b.length()>8)
            return false;
        for(int i=0;i<b.length();i++){
            if(b.charAt(i)!='0'&&b.charAt(i)!='1')
                return false;
        }
        return true;
    }
    private boolean isValidHex(String h){
        if(h.length()==0||h.length()>2)
            return false;
        for(int i=0;i<h.length();i++){
            if(!Character.isDigit(h.charAt(i)))
            {
                if(h.charAt(i)<'A'||h.charAt(i)>'Z')
                    return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * @param index
     * @param val
     * @param col d for byte, b for bin, h for hex, c for char, i for ci
     * @return 
     */
    public boolean editRow(int index, char col, String val){
        if(val==null)
            return false;
        //byte b h cr ci
        if(index<0||index>bCount)
            return false;
        try{
           switch(col){
               case 'd':
                   dec[index] = Byte.valueOf(val);                   
                   break;
               case 'b':
                   if(!isValidBin(val))
                       return false;
                   bin[index] = val;
                   break;
               case 'h':
                   if(!isValidHex(val))
                       return false;
                   hex[index] = val;
                   break;
               case 'c':
                   if(val.isEmpty()||val.length()>1)
                       return false;
                   chr[index] = val.charAt(0);
                   break;
               case 'i':
                   ci[index] = Integer.valueOf(val);
                   break;
               default:
                   return false;
           }
           syncRow(index, col);
           return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public boolean removeRows(int[] selectedRows){
        insertionSort(selectedRows);
        removeAt(selectedRows[0]);
        for(int i=1;i<selectedRows.length;i++){
            //for that -1:because for each remove, bCount will decrease by 1, we need to subtract i from the element(remove i times).
            if(!removeAt(selectedRows[i]-i)){
                return false;
            }
        }
        return true;
    }
    
    private void insertionSort(int[] arr){
        if(arr.length<2)
            return ;
        int i=1,j=0;
        while(i<arr.length){
            j=i-1;
            while(j>-1&&arr[j]>arr[j+1]){
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
                j--;
            }
            i++;
        }
    }
    
    public boolean removeAt(int index){
        if(index<0||index>bCount)
            return false;
        for(int i=index;i<bCount-1;i++){
            dec[i] = dec[i+1];
            bin[i]=bin[i+1];
            hex[i]=hex[i+1];
            chr[i]=chr[i+1];
            ci[i]=ci[i+1];
        }
        bCount--;
        return true;
    }
    
    public boolean insertEmptyRow(int index){
        if(index<0||index>bCount){
            return false;
        }
        if(index==bCount){
            addEmptyRow();
            return true;
        }
        if(!checkAvaliability()){
            extendArrs();
        }
        arrMoveBack(index);
        dec[index] = (byte)0;
        bin[index]="0";
        hex[index]="0";
        chr[index]=(char)0;
        ci[index]=0;
        bCount++;
        return true;
    }
    
    private void arrMoveBack(int index){
        for(int i=bCount;i>index;i--){
            dec[i] = dec[i-1];
            bin[i]=bin[i-1];
            hex[i]=hex[i-1];
            chr[i]=chr[i-1];
            ci[i]=ci[i-1];
        }
    }
    
    public Vector addEmptyRow(){
        addDec((byte)0);
        Vector eRow = new Vector();
        eRow.add(bCount);
        eRow.add(dec[bCount]);
        eRow.add(bin[bCount]);
        eRow.add(hex[bCount]);
        eRow.add(String.valueOf(chr[bCount]));
        eRow.add(ci[bCount]);
        return eRow;
    }
    
    private boolean checkAvaliability(){
        return bCount<totLen-1;
    }
    
    public void addDec(byte d){
        if(!checkAvaliability()){
            extendArrs();
        }
        dec[bCount++]=d;
        syncRow(bCount, 'd');
    }
    
    public void addBin(String b){
        if(!checkAvaliability()){
            extendArrs();
        }
        bin[bCount++]=b;
        syncRow(bCount, 'b');
    }
    
    public void addHex(String h){
        if(!checkAvaliability()){
            extendArrs();
        }
        hex[bCount++]=h;
        syncRow(bCount, 'h');
    }
    
    public void addChr(char c){
        if(!checkAvaliability()){
            extendArrs();
        }
        chr[bCount++]=c;
        syncRow(bCount, 'c');
    }
    
    public void addCi(int i){
        if(!checkAvaliability()){
            extendArrs();
        }
        ci[bCount++]=i;
        syncRow(bCount, 'i');
    }
    
    private void syncRow(int index, char newCol){
        switch(newCol){
            case 'd':
                //b h c ci
                bin[index] = byte2b(dec[index]);
                hex[index] = byte2h(dec[index]);
                chr[index] = byte2c(dec[index]);
                ci[index] = c2i(chr[index]);
                break;
            case 'b':
                //d h c ci
                dec[index] = b2byte(bin[index]);
                hex[index] = byte2h(dec[index]);
                chr[index] = byte2c(dec[index]);
                ci[index] = c2i(chr[index]);
                break;
            case 'h':                
                dec[index] = h2byte(hex[index]);
                bin[index] = byte2b(dec[index]);
                chr[index] = byte2c(dec[index]);
                ci[index] = c2i(chr[index]);
                break;
            case 'c':
                dec[index] = Byte.valueOf(c2byte(chr[index]));
                bin[index] = byte2b(dec[index]);
                hex[index] = byte2h(dec[index]);
                ci[index] = c2i(chr[index]);
                break;
            case 'i':
                bin[index] = Integer.toBinaryString(ci[index]);
                hex[index] = Integer.toHexString(ci[index]);
                dec[index] = b2byte(bin[index]);
                chr[index] = i2c(ci[index]);
                break;
        }
    }

    /**
     * byte数组转换为二进制字符串,每个字节以" "隔开 *
     */
    public String[] convertBinStr(byte[] b) {
        String[] result = new String[b.length];
        for (int i = 0; i < b.length; i++) {
            result[i] = Integer.toString(b[i] & 0xff, 2);
        }
        return result;
    }

    /**
     * 二进制字符串转换为byte数组,每个字节以" "隔开 *
     */
    public byte[] convertBinToByte(String hex2Str) {
        String[] temp = hex2Str.split(" ");
        byte[] b = new byte[temp.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp[i], 2).byteValue();
        }
        return b;
    }

    /**
     * byte数组转换为十六进制的字符串 *
     */
    public String[] convertHexStr(byte[] b) {
        String[] result = new String[b.length];
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            if ((b[i] & 0xff) < 0x10) {
                temp.append("0");
            }
            temp.append(Long.toString(b[i] & 0xff, 16));
            result[i] = temp.toString();
            temp.delete(0, temp.length());
        }
        return result;
    }

    /**
     * 十六进制的字符串转换为byte数组 *
     */
    public byte[] convertHexToByte(String hex16Str) {
        char[] c = hex16Str.toCharArray();
        byte[] b = new byte[c.length / 2];
        for (int i = 0; i < b.length; i++) {
            int pos = i * 2;
            b[i] = (byte) ("0123456789ABCDEF".indexOf(c[pos]) << 4 | "0123456789ABCDEF".indexOf(c[pos + 1]));
        }
        return b;
    }

    public byte[] charsToBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    public char[] bytesToChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    public int[] charToInt(char[] chrs) {
        int[] result = new int[chrs.length];
        for (int i = 0; i < chrs.length; i++) {
            result[i] = (int) chrs[i];
        }
        return result;
    }

    public int c2i(char c) {
        return (int) c;
    }

    public char i2c(int i) {
        return (char) i;
    }

    public String c2byte(char c) {
        return String.valueOf(charsToBytes(new char[]{c})[0]);
    }

    public String c2b(char c) {
        return Integer.toBinaryString((int)c);
    }

    public char byte2c(byte b) {
        return (char) b;
    }

    public String byte2b(byte b) {
        return convertBinStr(new byte[]{b})[0];
    }

    public String i2h(int d) {
        return Integer.toHexString(d);
    }

    public int h2i(String h) {
        return Integer.parseInt(h, 16);
    }
    
    public String byte2h(byte b){
        return convertHexStr(new byte[]{b})[0];
    }
    
    public byte h2byte(String h){
        return convertHexToByte(h)[0];
    }
    
    public byte b2byte(String b){
        return Long.valueOf(b, 2).byteValue();
    }
    
    public String i2b(int i){
        return Integer.toBinaryString(i);
    }
    
    public String h2b(String h){
        return Integer.toBinaryString(Integer.getInteger(h, 16));
    }
    
    public int b2i(String b){
        return Integer.valueOf(b,2);
    }
    
    public String b2h(String b){
        return Integer.toHexString(Integer.valueOf(b,2));
    }
}
