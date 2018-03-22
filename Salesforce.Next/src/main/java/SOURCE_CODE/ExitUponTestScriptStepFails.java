package SOURCE_CODE;

class ExitUponTestScriptStepFails extends Exception{
	public ExitUponTestScriptStepFails(String problem)
	{
		super(problem);
	}
}
