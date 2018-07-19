import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class FileUploadRetreiveService {

  constructor(private http: HttpClient) { }

  uploadFile(file:File,empId,type:String) {
    let url='http://localhost:8080/api/wiseconnect/v1/file/'+type;
    console.log('In File upload service');
    console.log(url)
    let formData = new FormData();
    formData.append('file', file);
    formData.append('empId', empId);

    let params = new HttpParams();

    const options = {
      params: params,
      reportProgress: true,
    };

    const req = new HttpRequest('POST', url, formData, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }

  getFile(empId,type:String){
    let url='http://localhost:8080/api/wiseconnect/v1/file/'+empId+'/'+type;
    return this.http.get(url,{responseType:'blob'});
  }
}

