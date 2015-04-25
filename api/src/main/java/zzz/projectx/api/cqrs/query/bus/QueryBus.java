package zzz.projectx.api.cqrs.query.bus;

import zzz.projectx.api.cqrs.query.Query;
import zzz.projectx.api.cqrs.query.QueryException;
import zzz.projectx.api.cqrs.query.QueryResult;

public interface QueryBus {

	<R extends QueryResult> R dispatch(Query<R> query) throws QueryException;

}
