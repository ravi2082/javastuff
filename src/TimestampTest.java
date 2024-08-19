import java.sql.Timestamp;

public class TimestampTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		System.out.println(ts.toString());
		//insert into table(request_dt) values('+ts.toString()+');
	}

}
