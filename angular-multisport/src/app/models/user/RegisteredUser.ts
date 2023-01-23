import { first } from "rxjs";
import { LoggedUser } from "./LoggedUser";

export class RegisteredUser extends LoggedUser {
    private firstName: string
    private secondName: string
    private age: number

    constructor(email: string, password: string, firstName: string, secondName: string, age: number) {
        super(email, password)
        this.firstName = firstName
        this.secondName = secondName
        this.age = age
    }

    setFirstName(firstName: string): void {
        this.firstName = firstName
    }

    getFirstName(): string {
        return this.firstName
    }

    setSecondName(secondName: string): void {
        this.secondName = secondName
    }

    getSecondName(): string {
        return this.secondName
    }

    setAge(age: number): void {
        this.age = age
    }

    getAge(): number {
        return this.age
    }
}