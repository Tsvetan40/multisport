export class User {
    private firstName: string;
    private secondName: string;
    private email: string;
    private password: string;
    private age: number;

    constructor(firstName: string, secondName: string, email:string, password: string, age: number) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public getFirstName(): string {
        return this.firstName;
    }

    public getSecondtName(): string {
        return this.secondName;
    }
    
    public getEmail(): string {
        return this.email;
    }
    
    public getPassword(): string {
        return this.password;
    }

    public getAge(): number {
        return this.age;
    }
}