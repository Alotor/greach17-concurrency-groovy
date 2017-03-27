package alotor.factorial

import org.apache.spark.*
import org.apache.spark.api.java.*

class Spark implements Factorial, ProductTrait {
    def context

    Spark() {
        def conf = new SparkConf()
            .setMaster("local[2]")
            .setAppName("FactorialSpark")
        this.context = new JavaSparkContext(conf)
    }

    BigInteger calculate(BigInteger number) {
        def result = context
            .parallelize(1g..number)
            .reduce({ a, b -> a * b }.dehydrate())

        return result
    }
}
