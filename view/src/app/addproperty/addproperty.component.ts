import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators, FormGroup, FormBuilder, FormControl } from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../Service/api.service";

@Component({
  selector: 'app-addproperty',
  templateUrl: './addproperty.component.html',
  styleUrls: ['./addproperty.component.css']
})
export class AddpropertyComponent implements OnInit {
  public propertyForm!: FormGroup;
  selectedImg: File | null = null;
  selectedImg1: File | null = null;
  selectedImg2: File | null = null;
  selectedImg3: File | null = null;

  private baseUrl = 'http://localhost:9000';

  constructor(
    private api: ApiService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.propertyForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      type: new FormControl(null, Validators.required),
      features: new FormControl(null),
      description: new FormControl(null, Validators.required),
      phone: new FormControl(null, Validators.required),
      price: new FormControl(null, Validators.required),
      location: new FormControl(null, Validators.required),
      owner_id: new FormControl(this.api.getUserId())
    });
  }

  public onFileChanged(event: any, imgNum: number = 0) {
    const file = event.target.files[0];
    if (!file) return;
    switch (imgNum) {
      case 0: this.selectedImg = file; break;
      case 1: this.selectedImg1 = file; break;
      case 2: this.selectedImg2 = file; break;
      case 3: this.selectedImg3 = file; break;
    }
    console.log(file);
  }

  Add() {
    if (this.propertyForm.invalid) {
      alert('Please fill all required fields.');
      return;
    }

    const requestBody: FormData = new FormData();
    requestBody.append("name", this.propertyForm.value.name);
    requestBody.append("type", this.propertyForm.value.type);
    requestBody.append("features", this.propertyForm.value.features || '');
    requestBody.append("description", this.propertyForm.value.description);
    requestBody.append("phone", this.propertyForm.value.phone);
    requestBody.append("location", this.propertyForm.value.location);

    if (this.selectedImg)  requestBody.append("image", this.selectedImg);
    if (this.selectedImg1) requestBody.append("image1", this.selectedImg1);
    if (this.selectedImg2) requestBody.append("image2", this.selectedImg2);
    if (this.selectedImg3) requestBody.append("image3", this.selectedImg3);

    const ownerId = this.propertyForm.value.owner_id;
    const price = this.propertyForm.value.price;

    this.http.post<any>(`${this.baseUrl}/owners/addProperty/${ownerId},${price}`, requestBody).subscribe(
      (res) => {
        console.log(res);
        alert("Property Added Successfully");
        this.propertyForm.reset();
        this.router.navigate(["ownerdashboard"]);
      },
      (err) => {
        alert("Property already added or something went wrong.");
      }
    );
  }
}
