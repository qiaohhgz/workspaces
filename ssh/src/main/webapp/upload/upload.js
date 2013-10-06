/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/6/13
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
function uploadFile(formId) {
    refreshSubTable(function(){
        alert("Upload success");
    });
    $(formId).submit();
}

function refreshSubTable(callback) {
    jFileUpload.getUploadFileResult(function (result) {
        if (result == true) {
            if(callback){
                callback();
            }
        } else if (result == null) {
            setTimeout(refreshSubTable, 2000);
        }
    });
}



