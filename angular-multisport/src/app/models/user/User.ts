import { RegisteredUser } from "./RegisteredUser";
import { Plan } from "../Plan";
import { AdminComment } from "../page/AdminComment";
import { Status } from "./Status";
import { Role } from "./Role";

export class User extends RegisteredUser{
    private _id: number
    private _salt: string
    private _plan: Plan
    private _comments: AdminComment[]

    constructor(email: string,
                password: string,
                firstName: string,
                secondName: string,
                age: number,
                id: number,
                salt: string,
                plan: Plan,
                comments: AdminComment[],
                status: Status,
                role: Role) {

        super(email, password, firstName, secondName, age, status, role)
        this._id = id
        this._salt = salt
        this._plan = plan
        this._comments = comments
    }

    get id (): number {
        return this._id
    }

    get salt(): string {
        return this._salt
    }

    get plan(): Plan {
        return this._plan
    }

    get comments(): AdminComment[] {
        return this._comments
    }
}