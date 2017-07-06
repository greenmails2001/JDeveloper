package com.vc.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public final class UnicodeToUnicodeSequence {
    private static final String[] unicodeLowerCase = 
    { "\u1EF9", "\u1EF7", "\u1EF5", "\u1EF3", "\u1EF1", "\u1EEF", "\u1EED", "\u1EEB", "\u1EE9", "\u1EE7", "\u1EE5", 
      "\u1EE3", "\u1EE1", "\u1EDF", "\u1EDD", "\u1EDB", "\u1ED9", "\u1ED7", "\u1ED5", "\u1ED3", "\u1ED1", "\u1ECF", 
      "\u1ECD", "\u1ECB", "\u1EC9", "\u1EC7", "\u1EC5", "\u1EC3", "\u1EC1", "\u1EBF", "\u1EBD", "\u1EBB", "\u1EB9", 
      "\u1EB7", "\u1EB5", "\u1EB3", "\u00F4", "\u1EAF", "\u1EAD", "\u1EAB", "\u1EA9", "\u1EA7", "\u1EA5", "\u1EA3", 
      "\u1EA1", "\u01B0", "\u01AF", "\u01A1", "\u01A0", "\u0169", "\u0129", "\u0111", "\u0103", "\u0102", "\u00FA", 
      "\u00F9", "\u00F5", "\u1EB1", "\u00F3", "\u00F2", "\u00ED", "\u00EC", "\u00EA", "\u00E9", "\u00E8", "\u00E3", 
      "\u00E2", "\u00E1", "\u00E0", "\u00D4", "\u0110", "\u00CA", "\u00C2", "\u00FD" };

    private static final String[] unicodeSequenceLowerCase = 
    { "\\\\u1EF9", "\\\\u1EF7", "\\\\u1EF5", "\\\\u1EF3", "\\\\u1EF1", "\\\\u1EEF", "\\\\u1EED", "\\\\u1EEB", 
      "\\\\u1EE9", "\\\\u1EE7", "\\\\u1EE5", "\\\\u1EE3", "\\\\u1EE1", "\\\\u1EDF", "\\\\u1EDD", "\\\\u1EDB", 
      "\\\\u1ED9", "\\\\u1ED7", "\\\\u1ED5", "\\\\u1ED3", "\\\\u1ED1", "\\\\u1ECF", "\\\\u1ECD", "\\\\u1ECB", 
      "\\\\u1EC9", "\\\\u1EC7", "\\\\u1EC5", "\\\\u1EC3", "\\\\u1EC1", "\\\\u1EBF", "\\\\u1EBD", "\\\\u1EBB", 
      "\\\\u1EB9", "\\\\u1EB7", "\\\\u1EB5", "\\\\u1EB3", "\\\\u00F4", "\\\\u1EAF", "\\\\u1EAD", "\\\\u1EAB", 
      "\\\\u1EA9", "\\\\u1EA7", "\\\\u1EA5", "\\\\u1EA3", "\\\\u1EA1", "\\\\u01B0", "\\\\u01AF", "\\\\u01A1", 
      "\\\\u01A0", "\\\\u0169", "\\\\u0129", "\\\\u0111", "\\\\u0103", "\\\\u0102", "\\\\u00FA", "\\\\u00F9", 
      "\\\\u00F5", "\\\\u1EB1", "\\\\u00F3", "\\\\u00F2", "\\\\u00ED", "\\\\u00EC", "\\\\u00EA", "\\\\u00E9", 
      "\\\\u00E8", "\\\\u00E3", "\\\\u00E2", "\\\\u00E1", "\\\\u00E0", "\\\\u00D4", "\\\\u0110", "\\\\u00CA", 
      "\\\\u00C2", "\\\\u00FD" };

    private static final String[] unicodeUpperCase = 
    { "\u00C0", "\u1EA2", "\u00C3", "\u00C1", "\u1EA0", "\u00C8", "\u1EBA", "\u1EBC", "\u00C9", "\u1EB8", "\u00CC", 
      "\u1EC8", "\u0128", "\u00CD", "\u1ECA", "\u00D2", "\u1ECE", "\u00D5", "\u00D3", "\u1ECC", "\u00D9", "\u1EE6", 
      "\u0168", "\u00DA", "\u1EE4", "\u1EF2", "\u1EF6", "\u1EF8", "\u00DD", "\u1EF4", "\u1EB0", "\u1EB2", "\u1EB4", 
      "\u1EAE", "\u1EB6", "\u1EA6", "\u1EA8", "\u1EAA", "\u1EA4", "\u1EAC", "\u1EC0", "\u1EC2", "\u1EC4", "\u1EBE", 
      "\u1EC6", "\u1ED2", "\u1ED4", "\u1ED6", "\u1ED0", "\u1ED8", "\u1EDC", "\u1EDE", "\u1EE0", "\u1EDA", "\u1EE2", 
      "\u1EEA", "\u1EEC", "\u1EEE", "\u1EE8", "\u1EF0" };


    private static final String[] unicodeSequenceUpperCase = 
    { "\\\\u00C0", "\\\\u1EA2", "\\\\u00C3", "\\\\u00C1", "\\\\u1EA0", "\\\\u00C8", "\\\\u1EBA", "\\\\u1EBC", 
      "\\\\u00C9", "\\\\u1EB8", "\\\\u00CC", "\\\\u1EC8", "\\\\u0128", "\\\\u00CD", "\\\\u1ECA", "\\\\u00D2", 
      "\\\\u1ECE", "\\\\u00D5", "\\\\u00D3", "\\\\u1ECC", "\\\\u00D9", "\\\\u1EE6", "\\\\u0168", "\\\\u00DA", 
      "\\\\u1EE4", "\\\\u1EF2", "\\\\u1EF6", "\\\\u1EF8", "\\\\u00DD", "\\\\u1EF4", "\\\\u1EB0", "\\\\u1EB2", 
      "\\\\u1EB4", "\\\\u1EAE", "\\\\u1EB6", "\\\\u1EA6", "\\\\u1EA8", "\\\\u1EAA", "\\\\u1EA4", "\\\\u1EAC", 
      "\\\\u1EC0", "\\\\u1EC2", "\\\\u1EC4", "\\\\u1EBE", "\\\\u1EC6", "\\\\u1ED2", "\\\\u1ED4", "\\\\u1ED6", 
      "\\\\u1ED0", "\\\\u1ED8", "\\\\u1EDC", "\\\\u1EDE", "\\\\u1EE0", "\\\\u1EDA", "\\\\u1EE2", "\\\\u1EEA", 
      "\\\\u1EEC", "\\\\u1EEE", "\\\\u1EE8", "\\\\u1EF0" };

    public static String convert(final String unicodeString) {
        String str = unicodeString;
        for (int i = 0; i < unicodeLowerCase.length; i++) {
            str = str.replaceAll(unicodeLowerCase[i], unicodeSequenceLowerCase[i]);
        }

        for (int i = 0; i < unicodeUpperCase.length; i++) {
            str = str.replaceAll(unicodeUpperCase[i], unicodeSequenceUpperCase[i]);
        }

        return str;
    }

    public static String readTextFile(final String inputFileName) throws IOException {
        final StringBuffer output = new StringBuffer();

        final BufferedReader buf = new BufferedReader(new FileReader(inputFileName));

        String intputStr = buf.readLine();
        while (intputStr != null) {
            output.append(intputStr);
            output.append("\n");

            intputStr = buf.readLine();
        }

        buf.close();
        return output.toString();
    }

    public static String convertFile(final String inputFileName, final String outputFileName) throws IOException {
        String filename = inputFileName;

        if (inputFileName.equalsIgnoreCase(outputFileName)) {
            final File input = new File(inputFileName);
            String newFileName = filename + "-" + System.currentTimeMillis() + ".bak";

            if (input.renameTo(new File(newFileName))) {
                filename = newFileName;
            } else {
                throw new IOException("Cannot create output file");
            }
        }

        final StringBuffer output = new StringBuffer();
        final BufferedReader buf = new BufferedReader(new FileReader(filename));

        final BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        String intputStr = buf.readLine();
        String converted;

        while (intputStr != null) {
            converted = UnicodeToUnicodeSequence.convert(intputStr);
            writer.write(converted);
            output.append(converted);
            output.append("\n");
            writer.newLine();
            intputStr = buf.readLine();
        }

        buf.close();
        writer.close();
        return output.toString();
    }
}
