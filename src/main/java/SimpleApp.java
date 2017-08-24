/**
 * Created by hyx on 2017/8/24.
 */
public class SimpleApp {
    public static void main(String[] args) {
        String logFile = "YOUR_SPARK_HOME/README.md"; // Should be some file on your system
        SparkSession spark = SparkSession.builder().appName("Simple Application").setMaster("local[2]").getOrCreate();
        Dataset<String> logData = spark.read.textFile(logFile).cache();

        long numAs = logData.filter(s -> s.contains("a")).count();
        long numBs = logData.filter(s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
    }
}
