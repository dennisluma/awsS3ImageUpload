package come.dennis.s3imageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import come.dennis.s3imageupload.AwsKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Configuration
public class AmazonConfig {

    String key;
    String id;

        public void awsKey(){
            try {
                File myObj = new File("awsaccesskey.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    id = data;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                File myObj2 = new File("awssecretkey.txt");
                Scanner myReader = new Scanner(myObj2);
                while (myReader.hasNextLine()) {
                    String data2 = myReader.nextLine();
                    key = data2;
                }
                myReader.close();
            } catch (FileNotFoundException e) {}
        }

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                AwsKey.getAccessKey(),
                AwsKey.getSecretKey()
        );
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("us-east-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
