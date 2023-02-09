import { Center } from "../centers/Center";
import { Article } from "./Article";

export class AdminComment {
    private _id: number
    private _article: Article | null
    private _center: Center | null
    private _content: string
    private _publishedAt: Date

    constructor() {
        this._id = 0
        this._article = null
        this._center = null
        this._content = ''
        this._publishedAt = new Date()
    }


    get id(): number {
        return this._id
    }

    get center(): Center | null {
        return this._center
    }

    get article(): Article | null {
        return this._article
    }

    get content(): string {
        return this._content
    }

    get publishedAt() : Date {
        return this._publishedAt
    }
}