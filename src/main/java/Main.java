import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.util.sketch.BloomFilter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        testMSSQL();
        return;

 //       SparkConf sparkConf = new SparkConf().setAppName("myApp").setMaster("local[*]");
//        SparkSession ss = SparkSession.builder().config(sparkConf).getOrCreate();
//
//        Dataset<Row> ds_car = ss
//                .read()
//                .format( "jdbc" )
//                .option( "url", "jdbc:postgresql://localhost:5432/dobrocot" )
//                .option( "dbtable", "(select * from car) vt" )
//                .option( "driver", "org.postgresql.Driver" )
//                .load();
//
//
//
//        BloomFilter bf = BloomFilter.create(100);
////        ds_car.foreach((ForeachFunction<Row>) row -> bf.putLong(row.getInt(2)));
//        List<Row> ls = ds_car.collectAsList();
//        for (Row row : ls) {
//            bf.putLong(row.getInt(2));
//        }
//
//
//        Dataset<Row> ds_mark = ss
//                .read()
//                .format( "jdbc" )
//                .option( "url", "jdbc:postgresql://localhost:5432/dobrocot" )
//                .option( "dbtable", "(select * from mark) vt" )
//                .option( "driver", "org.postgresql.Driver" )
//                .load();
//
//        ds_mark
//                .filter((FilterFunction<Row>) row -> !bf.mightContainLong(row.getInt(0)))
//                .select("ID")
//                .persist()
//                .show();

    }

    public static void testMSSQL(){
        SparkConf sparkConf = new SparkConf().setAppName("myApp").setMaster("local[*]");
        SparkSession ss = SparkSession.builder().config(sparkConf).getOrCreate();

        Dataset<Row> ds_car = ss
                .read()
                .format( "jdbc" )
                .option( "url", "jdbc:sqlserver://192.168.88.248;database=optimizm" )
                .option( "dbtable", "(select top 10 * from _InfoRg653) vt" )
                .option( "driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver" )
                .option("user", "sa")
                .option("password","123456789")
                .load();

        ds_car.show();
    }
}
