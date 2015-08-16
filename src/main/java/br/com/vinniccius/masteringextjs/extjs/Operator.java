package br.com.vinniccius.masteringextjs.extjs;

public enum Operator {
	
	EQUAL("="), 
	NOT_EQUAL("!="), 
	LESS_THAN("<"), 
	GREATER_THAN(">"), 
	LESS_EQUAL("<="), 
	GREATER_EQUAL(">="), 
	LIKE("%");
	
	private String operator;
	
	private Operator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public static Operator of(String value) {
		
		if (value == null) {
			return Operator.EQUAL;
		}
		
		if (value.isEmpty()) {
			return Operator.EQUAL;
		}
		
		if ("=".equals(value)) {
			return Operator.EQUAL;
		}
		if ("!=".equals(value)) {
			return Operator.NOT_EQUAL;
		}
		if ("<".equals(value)) {
			return Operator.LESS_THAN;
		}
		if (">".equals(value)) {
			return Operator.GREATER_THAN;
		}
		if ("<=".equals(value)) {
			return Operator.LESS_EQUAL;
		}
		if ("=>".equals(value)) {
			return Operator.GREATER_EQUAL;
		}
		
		return Operator.EQUAL;
	}
	
}
