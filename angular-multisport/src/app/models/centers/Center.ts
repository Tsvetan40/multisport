import { CenterType } from "./typecenter/CenterType"

export abstract class Center {
    private readonly centerType: CenterType
    private name: string
    private address: string
    private description: string
    private pictures: string[] = []

    constructor (name: string, address: string, description: string, pictures: string[], centerType: CenterType ) {
        this.name = name
        this.address = address
        this.description = description
        this.pictures = pictures
        this.centerType = centerType
    }

    public getCenterType(): CenterType {
        return this.centerType
    }

    public getPictures(): string[] {
        return this.pictures
    }

    public getName(): string {
        return this.name
    }

    public getAddress(): string {
        return this.address
    }

    public getDescription(): string {
        return this.description
    }
}