import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FileUploadRetreiveService } from '../file-upload-retreive.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  constructor(private fileUpload:FileUploadRetreiveService) { }

  ngOnInit() {
  }

  fileChange(event) {
    let fileList: File = event.target.files.item(0);
    this.fileUpload.uploadFile(fileList,"asd","xyz");
    
}

}
