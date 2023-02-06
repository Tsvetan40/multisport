import { CenterType } from "../centers/typecenter/CenterType"

export class Comment {
    private _content: string
    private _publishedAt: Date
    private _firstName: string
    private _lastName: string
    private _articleTitle: string | null
    private _centerAddress: string | null
    private _typeCenter: CenterType | null

    constructor() {
        this._content = ''
        this._publishedAt = new Date()
        this._firstName = ''
        this._lastName = ''
        this._articleTitle = null
        this._centerAddress = null
        this._typeCenter = null
    }

    withContent(content: string): Comment {
        this._content = content
        return this
    }

    withPusblishedAt(publishedAt: Date): Comment {
        this._publishedAt = publishedAt
        return this
    }

    withFirstname(firstName: string): Comment {
        this._firstName = firstName
        return this
    }

    withLastName(lastName: string): Comment {
        this._lastName = lastName
        return this
    }

    withArticleTitle(articleTitle: string): Comment {
        this._articleTitle = articleTitle
        return this
    }

    withCenterAddress(address: string): Comment {
        this._centerAddress = address
        return this
    }

    withTypeCenter(centerType: CenterType): Comment {
        this._typeCenter = centerType
        return this
    }

    get content(): string {
        return this._content
    }

    get firstName(): string {
        return this._firstName
    }

    get lastname(): string {
        return this._lastName
    }

    get articleTitle(): string | null {
        return this._articleTitle
    }

    get centerAddress(): string | null {
        return this._centerAddress
    }

    get typeCenter(): string | null {
        return this._typeCenter
    }
}