import { BulletinBoardPostComment } from './BulletinBoardPostComment';
import { Tag } from './Tag';

export class BulletinMessageCenter {
    id: number;
    author: any;
    text: string;
    postDate: string;
    category: any;
    department: any;
    comments: any;
    hotTopicYn: any;
    topic: string;
    bltPostComment: BulletinBoardPostComment[];
    tags: Tag[];
    isFavorited: boolean;
    isFavoritedYn: String;
    attachments: any[];
    image: string;
    lockYn: any;
    liked: boolean;
    likeCount: string;
    hasLikes: boolean;

}
