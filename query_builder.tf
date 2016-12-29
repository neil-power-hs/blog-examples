resource "aws_lambda_function" "query_builder" {

  s3_bucket = <Omitted>
  s3_key = "query_builder/${var.env}/query-builder-lambda-1.0.0.zip"

  lifecycle {
    ignore_changes = [
      "s3_key"
    ]
  }

  function_name = "queryBuilder_${var.env}"
  description = "Converts Twitter Search JSON to Twitter Search String and Vice Versa"
  runtime = "java8"

  timeout = 10
  role = "${aws_iam_role.query_builder_lambda_role.arn}"
  handler = "com.hootsuite.querybuilder.lambda.QueryBuilderLambda::parseInput"
}
