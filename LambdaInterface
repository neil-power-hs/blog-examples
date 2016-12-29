internal interface StagingLambdaInterface {
    
    @LambdaFunction(qualifier = "STAGING1_1")
    fun queryBuilder_staging(inputData: LambdaDataTransferObject): LambdaDataTransferObject
}

internal interface ProductionLambdaInterface {

    @LambdaFunction(qualifier = "PROD1_1")
    fun queryBuilder_production(inputData: LambdaDataTransferObject): LambdaDataTransferObject
}
