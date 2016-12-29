class AwsLambdaGenerator(val context: Context, lambdaType: LambdaType) : QueryStringGenerator {

    private val credentialsProvider: AWSCredentialsProvider
    private val factory: LambdaInvokerFactory
    private val awsLambdaEnvironmentInvoker: AwsLambdaEnvironmentInvoker

    init {
        credentialsProvider = LambdaCredentialsProvider(context)
        factory = LambdaInvokerFactory(context, Regions.US_EAST_1, credentialsProvider)
        awsLambdaEnvironmentInvoker = AwsLambdaEnvironmentInvoker(factory, lambdaType)
    }

    override fun generateQueryObject(searchParams: SearchParams): Observable<Query> {

        val lambdaObservable = Observable.fromCallable {
            val inputData = LambdaDataTransferObject(searchParams)
            awsLambdaEnvironmentInvoker.invokeLambda(inputData).getQueryObject()
        }
        return lambdaObservable
    }

    override fun generateSearchParams(query: Query): Observable<SearchParams> {
        val lambdaObservable = Observable.fromCallable {
            val inputData = LambdaDataTransferObject(query)
            awsLambdaEnvironmentInvoker.invokeLambda(inputData).getSearchParams()
        }
        return lambdaObservable
    }
}
