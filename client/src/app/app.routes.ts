import { Routes } from '@angular/router';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { IntroComponent } from './pages/intro/intro.component';
import { AddComponent } from './pages/add/add.component';
import { ListComponent } from './pages/list/list.component';
import { EditComponent } from './pages/edit/edit.component';

export const routes: Routes = [
    { path: 'intro', component: IntroComponent },
    { path: 'add', component: AddComponent },
    { path: 'edit', component: EditComponent },
    { path: 'list', component: ListComponent },
    { path: '', redirectTo: '/intro', pathMatch: 'full' },
    { path: '**', component: PageNotFoundComponent },
];
