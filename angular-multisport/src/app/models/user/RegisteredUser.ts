import { LoggedUser } from "./LoggedUser";
import { Role } from "./Role";
import { Status } from "./Status";

export class RegisteredUser extends LoggedUser {
    private _firstName: string
    private _secondName: string
    private _age: number
    private _status: Status
    private _role: Role

    constructor(email: string, password: string, firstName: string, secondName: string, age: number, status: Status, role: Role) {
        super(email, password)
        this._firstName = firstName
        this._secondName = secondName
        this._age = age
        this._status = status
        this._role = role
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

    setStatus(status: Status) {
        this._status = status
    }

    get status(): Status {
        return this._status
    }

    setRole(role: Role) {
        this._role = role
    }

    get role(): Role {
        return this._role
    }
}