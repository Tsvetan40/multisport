import { withDisabledInitialNavigation } from "@angular/router"

export class Article {
    private _title: string
    private _content: string
    private _pictureBase64: string
    private _publishedAt: Date

    constructor() {
        this._title = ''
        this._content = ''
        this._pictureBase64 = ''
        this._publishedAt = new Date()
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
        console.log(typeof this.publishedAt, 'getter')
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
}