import { CenterType } from "./typecenter/CenterType"

export abstract class Center {
    private readonly _centerType: CenterType
    private _name: string
    private _address: string
    private _description: string
    private _pictures: string[] = []

    constructor (name: string, address: string, description: string, pictures: string[], centerType: CenterType ) {
        this._name = name
        this._address = address
        this._description = description
        this._pictures = pictures
        this._centerType = centerType
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
}