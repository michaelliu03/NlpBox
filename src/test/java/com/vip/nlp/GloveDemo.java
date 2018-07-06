package com.vip.nlp;

import com.vip.nlp.glove.Cooccurrence;
import com.vip.nlp.glove.GloVe;
import com.vip.nlp.glove.Vocabulary;
import com.vip.nlp.util.Methods;
import com.vip.nlp.util.Options;
import com.vip.nlp.util.StoreFileObject;
import org.jblas.DoubleMatrix;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author michael.liu
 * @Title: GloveDemo
 * @ProjectName nlpbox
 * @Description: glove train
 * @date 2018/7/511:42
 */


public class GloveDemo {

    private static java.util.logging.Logger log = Logger.getLogger(GloveDemo.class.getName());

    public static void train(){
        String file = "data/output2.txt";

        Options options = new Options();
        options.debug = true;
        log.info("正在执行，请稍后......");

        Vocabulary vocab = GloVe.build_vocabulary(file, options);
        options.window_size = 3;
        List<Cooccurrence> c =  GloVe.build_cooccurrence(vocab, file, options);

        options.iterations = 50;
        options.vector_size = 10;
        options.debug = true;
        DoubleMatrix W = GloVe.train(vocab, c, options);

        try {
            StoreFileObject.writeObject("model/matrix.model", W);
            StoreFileObject.writeObject("model/vocab.cab", vocab);
        } catch (IOException e) {
            log.info("train save failure!" + e.getMessage());
        }

       log.info("执行结束");
    }

    public static void getSimilarWords(){
        try {
            Vocabulary vocab = (Vocabulary) StoreFileObject.readObject("model/vocab.cab");
            DoubleMatrix W=(DoubleMatrix) StoreFileObject.readObject("model/matrix.model");

            List<String> similars = Methods.most_similar(W, vocab, "飞利浦", 10);
            for(String similar : similars) {
                System.out.println(similar);
            }

        } catch (Exception e) {
            log.warning("getSimilarWords" + e.getMessage());
        }


    }

    public static void main(String [] args){
        //train();
        getSimilarWords();
    }
}