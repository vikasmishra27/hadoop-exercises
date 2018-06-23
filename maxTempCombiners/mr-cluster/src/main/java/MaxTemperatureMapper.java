// cc MaxTemperatureMapper Mapper for maximum temperature example
// vv MaxTemperatureMapper
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {



  private static final int MISSING = 9999;
  //Counters definition 
  enum Temperature {
       MALFORMED,
       BADRANGE,
       BADYEAR
  }
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

	  String line = value.toString();
		  if (line.length() < 93) {//Check if record length is with-in expected range
			  context.getCounter(Temperature.MALFORMED).increment(1);// Set MALFORMED counter
		  } else {
			  String year = line.substring(15, 19);
			  int airTemperature;
			  if(((line.charAt(87) == '+') && CheckString(line.substring(88, 92))) || (CheckString(line.substring(87, 92))))//Check for valid Temperature
			  {
			  	if (CheckString(year)) {//Check for valid Year
			  		if (line.charAt(87) == '+') { // parseInt doesn't like leading plus signs
				  		airTemperature = Integer.parseInt(line.substring(88, 92));
			  		} else {
				  	airTemperature = Integer.parseInt(line.substring(87, 92));
			  		}
			  		String quality = line.substring(92, 93);
			  		if (quality.matches("[01459]")) {//validate quality value
				  		//if (Integer.parseInt(year) >= 1950 && Integer.parseInt(year) <= 1990) {
				  		if (Integer.parseInt(year) >= 1960 && Integer.parseInt(year) <= 1990) {
					  		if (airTemperature <= 500) {//Check for expected maximum temperature
						  		context.write(new Text(year), new IntWritable(airTemperature));
					  		} else {
						  		context.getCounter(Temperature.BADRANGE).increment(1);//Set unexpected Temperature counter
					  			}	
				  			} else {
					  			context.getCounter(Temperature.BADYEAR).increment(1);//Set BAD Year Counter
				  				}
			  		} else {
				  		context.getCounter(Temperature.MALFORMED).increment(1); // Set Malformed Counter
			  		}
		  		}else {
				context.getCounter(Temperature.BADYEAR).increment(1);// Check BADYEAR Counter
				}
			} else {
				context.getCounter(Temperature.MALFORMED).increment(1);//Check MALFORMED Counter
			}
		}
  	}

//Function to Validate Numeric String
public static boolean CheckString(String str) {
    for (char c : str.toCharArray()) {
        if (!Character.isDigit(c))
            return false;
    }
    return true;
}
}

// ^^ MaxTemperatureMapper
