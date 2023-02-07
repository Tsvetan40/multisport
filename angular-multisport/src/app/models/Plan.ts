export class Plan {
    private _name: string
    private _price: number
    private _centersAddresses: string[]
    private _imageBase64!: string

    constructor (name: string, price: number, centersAddresses: string[]) {
        this._name = name
        this._price = price
        this._centersAddresses = centersAddresses
    }

    public get centersAddresses(): string[] {
        return this._centersAddresses
    }

    public get price(): number {
        return this._price
    }

    public get name(): string {
        return this._name
    }

    public setImageBase64 (imageBase64: string) {
        this._imageBase64 = imageBase64
    }

    public get imageBase64() {
        return this._imageBase64
    }
}