import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable, BehaviorSubject } from "rxjs";
import { User } from "../models/User";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class LoginService {
  private loginurl = "http://localhost:8080/40forty/login";

  private registerurl = "http://localhost:8080/40forty/register";

  private sesurl =
    "http://localhost:9080/Project2/getSessionUser.MasterServlet";

  public currentUser: Observable<User> = null;
  private currentUserSubject: BehaviorSubject<User>;

  //public isloggedin = false;

  constructor(private httpCli: HttpClient, private router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem("currentUser"))
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  getCurrentUser(): User {
    return this.currentUserSubject.value;
  }

  doLogin(uname: string, pword: string): Observable<User> 
  {
    const options = { 
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      withCredentials : true
    }

    const payload = new HttpParams()
      .set("username", uname)
      .set("password", pword);

    const payload2 = `{
      "username" : "${uname}",
      "password" : "${pword}"
    }`;
    
    const res = this.httpCli.post<User>(this.loginurl, payload2, options);
    let user;
    res.subscribe(data => {
      user = data;
      // console.log("User:" + user);
      if (user) {
        //console.log("User is not null");
        this.currentUserSubject.next(user);
        localStorage.setItem("currentUser", JSON.stringify(data));
        this.router.navigate(["feed"]);
      } else {
        this.router.navigate(["login"]);
      }
    });
    return res;
  }

  doLogout() {
    localStorage.removeItem("currentUser");
    this.currentUserSubject.next(null);
    window.location.reload();
  }

  doRegister(email, uname, password, firstname, lastname, pic) 
  {
    console.log("Pic is: " + pic);

    let options = { 
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }

    // const payload = new HttpParams()
    //   .append("email", email)
    //   .append("username", uname)
    //   .append("password", password)
    //   .append("firstName", firstname)
    //   .append("lastName", lastname)
    //   .append("avatarURL", pic);

    const payload2 = `{
      "email" : "${email}",
      "username" : "${uname}",
      "password" : "${password}",
      "firstName" : "${firstname}",
      "lastName" : "${lastname}",
      "avatarURL" : "${pic}"
    }`

    console.log(payload2);

    const res = this.httpCli.post(this.registerurl, payload2, options);

    let user;
    res.subscribe(data => {
      user = data;
      // console.log("User:" + user);
      if (user) {
        //console.log("User is not null");
        this.currentUserSubject.next(user);
        localStorage.setItem("currentUser", JSON.stringify(data));
        this.router.navigate(["feed"]);
      } else {
        this.router.navigate(["login"]);
      }
    });

    return res;
  }
}
