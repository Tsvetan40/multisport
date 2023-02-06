import { CenterType } from "./typecenter/CenterType"
import { Comment } from "../page/Comment"
export abstract class Center {
    private readonly _centerType: CenterType
    private _id: number
    private _name: string
    private _address: string
    private _description: string
    private _pictures: string[] = []
    private _rating: number
    private _comments: Comment[]

    constructor (id: number, name: string, address: string, description: string, pictures: string[], centerType: CenterType, rating: number, comments: Comment[] ) {
        this._id = id
        this._name = name
        this._address = address
        this._description = description
        this._pictures = pictures
        this._centerType = centerType
        this._rating = rating
        this._comments = comments
    }

    public get rating(): number {
        return this._rating
    }

    public get id(): number {
        return this._id
    }

    public get centerType(): CenterType {
        return this.centerType
    }

    public get pictures(): string[] {
        return this._pictures
    }

    public get name(): string {
        return this._name
    }

    public get address(): string {
        return this._address
    }

    public get description(): string {
        return this._description
    }
    
    public get comments(): Comment[] {
        return this._comments
    }
}