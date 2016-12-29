class AwsLambdaEnvironmentInvoker(factory: LambdaInvokerFactory, val lambdaType: LambdaType) {

    private val stagingLambdaInterface: StagingLambdaInterface?
    private val productionLambdaInterface: ProductionLambdaInterface?

    init {
        when (lambdaType) {
            LambdaType.STAGING -> {
                stagingLambdaInterface = factory.build(StagingLambdaInterface::class.java)
                productionLambdaInterface = null
            }
            LambdaType.PRODUCTION -> {
                stagingLambdaInterface = null
                productionLambdaInterface = factory.build(ProductionLambdaInterface::class.java)
            }
        }
    }
    
    fun invokeLambda(inputData: QueryBuilderLambdaInputData): String {
        when (lambdaType) {
            LambdaType.STAGING -> return stagingLambdaInterface!!.queryBuilder_staging(inputData)
            LambdaType.PRODUCTION -> return productionLambdaInterface!!.queryBuilder_production(inputData)
        }
    }
}
