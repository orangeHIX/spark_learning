
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * Created by hyx on 2017/8/25.
 */
public class LogisticRegression {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("Simple Application").master("local[2]").getOrCreate();
// Load training data
        Dataset<Row> training = spark.read().format("libsvm")
                .load("data/mllib/sample_libsvm_data.txt");

        LogisticRegression lr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(0.3)
                .setElasticNetParam(0.8);

// Fit the model
        LogisticRegressionModel lrModel = lr.fit(training);

// Print the coefficients and intercept for logistic regression
        System.out.println("Coefficients: "
                + lrModel.coefficients() + " Intercept: " + lrModel.intercept());

// We can also use the multinomial family for binary classification
        LogisticRegression mlr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(0.3)
                .setElasticNetParam(0.8)
                .setFamily("multinomial");

// Fit the model
        LogisticRegressionModel mlrModel = mlr.fit(training);

// Print the coefficients and intercepts for logistic regression with multinomial family
        System.out.println("Multinomial coefficients: " + lrModel.coefficientMatrix()
                + "\nMultinomial intercepts: " + mlrModel.interceptVector());

        spark.stop();
    }
}
}
