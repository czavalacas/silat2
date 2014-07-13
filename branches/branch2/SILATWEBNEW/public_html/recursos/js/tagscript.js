function addWatermarkBehavior(componentId, type, text) {
    var sourceInput = AdfPage.PAGE.findComponent(componentId);
    var domElemement = AdfAgent.AGENT.getElementById(sourceInput.getClientId());
    var targetArray = domElemement.getElementsByTagName(type);
    targetArray[0].placeholder = text;
}
