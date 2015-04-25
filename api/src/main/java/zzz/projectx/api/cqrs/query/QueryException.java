package zzz.projectx.api.cqrs.query;

import static com.google.common.base.Preconditions.checkArgument;

public class QueryException extends RuntimeException {

	private static final long serialVersionUID = -1829267731142620783L;

	private final Query<?> query;

	public QueryException(final Query<?> query, final Throwable cause) {
		super(cause);
		checkArgument(query != null, "query can not be null");
		this.query = query;
	}

	public Query<?> getQuery() {
		return this.query;
	}

}
