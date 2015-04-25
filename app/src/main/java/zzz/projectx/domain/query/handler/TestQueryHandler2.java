package zzz.projectx.domain.query.handler;

import zzz.projectx.api.cqrs.query.handler.QueryHandler;
import zzz.projectx.application.query.TestQuery2;
import zzz.projectx.application.query.TestQueryResult2;

public class TestQueryHandler2 implements QueryHandler<TestQuery2, TestQueryResult2> {

	@Override
	public TestQueryResult2 handle(final TestQuery2 query) throws Exception {
		System.out.println("TestQueryHandler2.handle()");
		return new TestQueryResult2();
	}

	@Override
	public Class<TestQuery2> queryType() {
		return TestQuery2.class;
	}

}
