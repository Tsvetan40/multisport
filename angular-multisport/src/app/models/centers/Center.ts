export abstract class Center {
    private name: string;
    private address: string;
    private description: string;

    constructor (name: string, address: string, description: string) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public getName(): string {
        return this.name;
    }

    public getAddress(): string {
        return this.address;
    }

    public getDescription(): string {
        return this.description;
    }
}