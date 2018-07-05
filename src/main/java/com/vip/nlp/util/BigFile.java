package com.vip.nlp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

/**
 * @author michael.liu
 * @Title: BigFile
 * @ProjectName nlpbox
 * @Description: TODO
 * @date 2018/7/511:23
 */


public class BigFile implements Iterable<String> {
    private BufferedReader reader;

    public BigFile(String filePath) throws Exception
    {
        reader = new BufferedReader(new FileReader(filePath));
    }

    public void Close()
    {
        try
        {
            reader.close();
        }
        catch (Exception ex) {

        }
    }

    @Override
    public Iterator<String> iterator() {
        return new FileIterator();
    }

    private class FileIterator implements Iterator<String>
    {
        private String currentLine;

        public boolean hasNext()
        {
            try
            {
                currentLine = reader.readLine();
            }
            catch (Exception ex)
            {
                currentLine = null;
                ex.printStackTrace();
            }

            return currentLine != null;
        }

        public String next()
        {
            return currentLine;
        }

        public void remove()
        {
        }
    }
}