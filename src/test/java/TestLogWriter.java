import java.io.IOException;
import java.util.Date;

import com.huhang.userlevel.log.LogWriter;
import com.huhang.userlevel.interceptor.ActionLog;
import org.dom4j.DocumentException;
import org.junit.Test;

public class TestLogWriter {
	public static void main(String[] args){

	}
	@Test
	public void testLogWriter() throws IOException, DocumentException{
		ActionLog actionLog=new ActionLog();
		actionLog.setName("login");
		Date startTime=new Date();//get current time
		actionLog.setStartTime(startTime);
		Date endTime=new Date();
		actionLog.setEndTime(endTime);
		actionLog.setResult("success");
		String path="src/log.xml";
		LogWriter logWriter=new LogWriter();
		logWriter.log(actionLog);
	}
}
