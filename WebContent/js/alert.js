/**
 * 警告框操作信息，ID必须为“alertDiv”
 * @param flag 操作成功或失败的标记
 * @param suctext 操作成功时的显示文本内容
 * @param faltext 操作失败时的显示文本内容
 */
function operateAlert(flag,suctext,faltext) {
	if (flag) {
		$("#alertDiv").attr("class","alert fixed-bottom alert-success") ;
		$("#alertText").text(suctext) ;
	} else {
		$("#alertDiv").attr("class","alert fixed-bottom alert-danger") ;
		$("#alertText").text(faltext) ;
	}
	$("#alertDiv").fadeIn(1000,function(){
        $("#alertDiv").fadeOut(2000) ;
    }) ;
}