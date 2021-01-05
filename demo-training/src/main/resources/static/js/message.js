var messages = new Array();
messages["M02"] = "施設情報を登録しますか。";
messages["M03"] = "施設情報を更新しますか。";
messages["M04"] = "検査依頼を登録しますか。";
messages["M04_01"] = "バーコードがありません。<br\>検査依頼を登録しますか。";

function getMsg(id) {
	var str = "";
	if ( messages[ id ] != undefined ) {
		str = messages[ id ];
	}
	return str;
}
