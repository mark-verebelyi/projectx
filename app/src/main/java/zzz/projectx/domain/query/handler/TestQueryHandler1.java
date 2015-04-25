package zzz.projectx.domain.query.handler;

import zzz.projectx.api.cqrs.query.handler.QueryHandler;
import zzz.projectx.application.query.TestQuery1;
import zzz.projectx.application.query.TestQueryResult1;

public class TestQueryHandler1 implements QueryHandler<TestQuery1, TestQueryResult1> {

	@Override
	public TestQueryResult1 handle(final TestQuery1 query) throws Exception {
		System.out.println("TestQueryHandler1.handle()");
		return new TestQueryResult1();
	}

	@Override
	public Class<TestQuery1> queryType() {
		return TestQuery1.class;
	}

}
