// Import Statements
import java.io.IOException;
import com.cloudera.sqoop.lib.RecordParser.ParseError;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;

//Class AvgWidgetTemp Defined
public class AvgWidgetTemp extends Configured implements Tool {

  // Input Key => LongWritable, Input Value => Text(Widget), Output Key => IntWritable, Output Value => IntWritable
  public static class MaxWidgetMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

    //Local Variable definition
    private IntWritable result = new IntWritable();
		
    // Define Map function 
    public void map(LongWritable k, Text v, Context context)throws IOException, InterruptedException {
      Widget widget = new Widget();
      try {
        widget.parse(v); // Auto-generated: parse all fields from text.
      } catch (ParseError pe) {
        // Got a malformed record. Ignore it.
        return;
      }
      //Capture AIR_TEMP from widget
      Integer id = widget.get_AIR_TEMP().intValue(); 
      result.set(id);// Convert Integer to IntWritable
      context.write(new IntWritable(0), result); // Send to reducer	  
    }
  }

  public static class MaxWidgetReducer
      extends Reducer<IntWritable, IntWritable, FloatWritable, NullWritable> {

    public void reduce(IntWritable k, Iterable<IntWritable> vals, Context context)
        throws IOException, InterruptedException {
      
      //Define Local Variable
      Float maxWidget = 0.00f;
      IntWritable result = new IntWritable();
      Integer count = 0;

      // Read Each Map value and calculate Count and Sum
      for (IntWritable w : vals) {
         maxWidget = maxWidget + w.get();
                 count += 1;
      }
      Float avg_m = (float) (maxWidget/count); //Calculate Average
     
      context.write(new FloatWritable(avg_m), NullWritable.get()); // Write to output
    }
  }

  public int run(String [] args) throws Exception {
    Job job = new Job(getConf());

    job.setJarByClass(AvgWidgetTemp.class);

    job.setMapperClass(MaxWidgetMapper.class);
    job.setReducerClass(MaxWidgetReducer.class);

    FileInputFormat.addInputPath(job, new Path("widgets"));
    FileOutputFormat.setOutputPath(job, new Path("average_temperature"));

    job.setMapOutputKeyClass(IntWritable.class);
    job.setMapOutputValueClass(IntWritable.class);

    job.setOutputKeyClass(FloatWritable.class);
    job.setOutputValueClass(NullWritable.class);

    job.setNumReduceTasks(1);

    if (!job.waitForCompletion(true)) {
      return 1; // error.
    }

    return 0;
  }

  public static void main(String [] args) throws Exception {
    int ret = ToolRunner.run(new AvgWidgetTemp(), args);
    System.exit(ret);
  }
}
