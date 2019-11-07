import org.springframework.cloud.contract.spec.Contract

Contract.make {
    input {
        triggeredBy("triggerMethod()")
    }
    outputMessage {
        sentTo("output")
        body([
                content: $(regex(alphaNumeric()))
        ])
    }
}