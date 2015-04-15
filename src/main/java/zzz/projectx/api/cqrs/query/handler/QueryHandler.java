package zzz.projectx.api.cqrs.query.handler;

import zzz.projectx.api.cqrs.query.Query;
import zzz.projectx.api.cqrs.query.QueryResult;

public interface QueryHandler<Q extends Query<R>, R extends QueryResult> {

	R handle(Q query) throws Exception;

	Class<Q> queryType();

}
