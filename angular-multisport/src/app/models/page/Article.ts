import { Comment } from "./Comment"

export class Article {
    private _title: string
    private _content: string
    private _pictureBase64: string
    private _publishedAt: Date
    private _comments: Comment[]

    constructor() {
        this._title = ''
        this._content = ''
        this._pictureBase64 = ''
        this._publishedAt = new Date()
        this._comments = []
    }

    withTitle(title: string): Article {
        this._title = title
        return this
    }

    withPublishedAt(publishedAt: Date) {
        this._publishedAt = publishedAt
        return this
    }

    withContent(content: string): Article {
        this._content = content
        return this
    }

    withPictureBase64(pictureBase64: string): Article {
        this._pictureBase64 = pictureBase64
        return this
    }

    withComments(comments: Comment[]): Article {
        this._comments = comments
        return this
    }

    get title(): string {
        return this._title
    }

    get content(): string {
        return this._content
    }

    get pictureBase64() : string {
        return this._pictureBase64
    }

    get publishedAt(): Date {
        return this._publishedAt
    }

    get comments(): Comment[] {
        return this._comments
    }
}