import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

//Assignment 4: Thread Pool Image Processor
//Background:
//Simulate a parallel image processing pipeline using Java’s ExecutorService.
//Requirements:
//Given 50 image IDs (1–50), simulate processing (sleep 100ms)
//Return a message "Image X processed" for each
//Collect and print results in order using Callable + Future
//Bonus Requirements:
//Use a fixed thread pool of size 5
//Measure total execution time

class ImageProcessor implements Callable<String> {
    private int ImageNumber;
    ImageProcessor(int ImageNumber) {
        this.ImageNumber = ImageNumber;
    }
    @Override
    public String call() throws Exception{
        Thread.sleep(1000);
        return "Image " + ImageNumber + " Processed!";
    }
}
public class q3 {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for(int i=1;i<=50;i++){
            Callable<String> task = new ImageProcessor(i);
            Future<String> future = executor.submit(task);
            list.add(future);
        }
        for(Future<String> f : list){
            try{
                System.out.println(f.get());
            }
            catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        long endTime = System.currentTimeMillis(); // End time
        System.out.println("\nTotal Execution Time: " + (endTime - startTime) + " ms");
    }
}
