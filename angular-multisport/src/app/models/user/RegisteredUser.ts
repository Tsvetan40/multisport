import { LoggedUser } from "./LoggedUser";

export class RegisteredUser extends LoggedUser {
    private _firstName: string
    private _secondName: string
    private _age: number

    constructor(email: string, password: string, firstName: string, secondName: string, age: number) {
        super(email, password)
        this._firstName = firstName
        this._secondName = secondName
        this._age = age
    }

    setFirstName(firstName: string): void {
        this._firstName = firstName
    }

    get firstName(): string {
        return this._firstName
    }

    setSecondName(secondName: string): void {
        this._secondName = secondName
    }

    get secondName(): string {
        return this._secondName
    }

    setAge(age: number): void {
        this._age = age
    }

    get age(): number {
        return this._age
    }
}