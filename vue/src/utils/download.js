export function blobDownload(response){
    var contentTypeHeader = response.data.type;
    var blobURL = window.URL.createObjectURL(new Blob([response.data], { type: contentTypeHeader }))
    var fileName = response.headers['content-disposition'].match(/filename="(.+)"/)[1]
    var downloadLink = window.document.createElement('a');

    fileName =  decodeURIComponent(fileName)   
    downloadLink.href = blobURL;
    downloadLink.download = fileName;
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}