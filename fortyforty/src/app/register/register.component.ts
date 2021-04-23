import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../shared/login.service';
import { UploadService } from '../shared/upload.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private username = '';
  private password = '';
  private confirm_pass = '';
  private firstname = '';
  private lastname = '';
  private email = '';
  private selectedFile;
  private avatar = '';
  fail = false;



  constructor(private loginServ: LoginService, private router: Router, private uploadServ: UploadService) { }

  ngOnInit() {
  }

  onFileSelected(event){
    // this.selectedFile = event.target.files[0] as File;
    // console.log(this.selectedFile);
  }

  onPress() 
  {
    // Added so uploading only happens if the file is present
    // ...but it would fail if it's there because the doUpload method
    // in the login.service.ts needs a currentuser in the session FIRST
    // if(!this.selectedFile === null)
    // {
    //   this.uploadFile(this.selectedFile);
    // }
    //
    // I commented out all register profile pic input features

    this.register();
  }

  uploadFile(file) {
    // this.avatar = this.uploadServ.doUpload(file);
    // this.register();
  }

  register() {
    if (this.password === this.confirm_pass) {
      console.log(this.email);
      this.loginServ.doRegister(this.email, this.username, this.password, this.firstname, this.lastname, this.avatar).subscribe(data => {
        console.log(data);
        if (data['success']) {
          this.router.navigate(['login']);
        } else {
          this.fail = true;
        }
      });

    } else {
      console.log("Screw you buddy");
    }
  }

}
