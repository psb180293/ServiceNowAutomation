package servicenow;

public class RunMe {

	public static void main(String[] args) throws InterruptedException {
		
		
		
		launchServiceNow lsn = new launchServiceNow();
		
		String username = "admin";
		String password= "Jan@2020!";
		String url="https://dev62656.service-now.com/";
		String chromepath="E:\\Selenium\\chromedriver_win32\\chromedriver.exe";
		
		
		lsn.startServiceNow(url, username, password, chromepath);
		// TODO Auto-generated method stub

	}

}
